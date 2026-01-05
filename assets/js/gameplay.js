function createEnemyStyles() {
    return [
        { type: 'grunt', bodyColor: '#c0392b', headRadius: 5, bodyLength: 14, armLength: 10, legLength: 12, hatType: 'triangle', hatColor: '#f1c40f', hatSizeBoost: 4, weapon: 'gloves', armor: 'padded' },
        { type: 'brute', bodyColor: '#8e44ad', headRadius: 6, bodyLength: 18, armLength: 12, legLength: 14, hatType: 'square', hatColor: '#2980b9', hatSizeBoost: 4, weapon: 'sword', armor: 'plate' },
        { type: 'scout', bodyColor: '#e67e22', headRadius: 4, bodyLength: 12, armLength: 11, legLength: 16, hatType: 'pentagon', hatColor: '#1abc9c', hatSizeBoost: 4, weapon: 'cup', armor: 'mesh' }
    ];
}

const ITEM_CATALOG = {
    weapons: {
        gloves: { label: 'Boxing Gloves', value: 30 },
        sword: { label: 'Recruit Sword', value: 55 },
        cup: { label: 'Steel Coffee Mug', value: 25 },
    },
    armor: {
        padded: { label: 'Padded Vest', value: 35 },
        plate: { label: 'Reinforced Plate', value: 60 },
        mesh: { label: 'Light Mesh', value: 40 },
    },
};

function rectDistance(px, py, rect) {
    const cx = Math.max(rect.x, Math.min(px, rect.x + rect.width));
    const cy = Math.max(rect.y, Math.min(py, rect.y + rect.height));
    return Math.hypot(px - cx, py - cy);
}

export function createGameplaySystem(settings, keyState, joystickState, lookJoystickState, actionState, debugState = {}) {
    debugState.showColliders = Boolean(debugState.showColliders);
    debugState.freezeOnCollision = debugState.freezeOnCollision !== false;
    const player = { x: 100, y: 100, radius: 10, speed: 2, color: 'blue', facing: -Math.PI / 2 };
    const enemies = [];
    const enemyStyles = createEnemyStyles();
    let playerCentered = false;
    let killCount = 0;
    let mission = 1;
    let extractionReady = false;
    const targetZone = { x: 200, y: 200, size: 50 };

    const inventory = { credits: 150, items: { weapons: {}, armor: {} }, equipped: { weapon: null, armor: null } };
    const notifications = [];

    const level2State = {
        containment: { x: 120, y: 120, size: 320 },
        charges: [],
        planted: [],
        carryingCharge: false,
        blastRadius: 60,
        walls: [],
    };

    function notify(text) {
        notifications.push({ text, timer: 1500 });
    }

    function consumeNotifications() {
        const copy = notifications.splice(0, notifications.length);
        return copy;
    }

    function pickEnemyStyle() {
        return enemyStyles[Math.floor(Math.random() * enemyStyles.length)];
    }

    function computeEnemyCount() {
        const area = window.innerWidth * window.innerHeight;
        if (area < 450000) return 7;
        if (area < 900000) return 10;
        return 14;
    }

    function spawnEnemy(style) {
        const ex = Math.random() * window.innerWidth;
        const ey = Math.random() * window.innerHeight;
        const evx = (Math.random() - 0.5) * 2;
        const evy = (Math.random() - 0.5) * 2;
        const hitRadius = Math.max(style.headRadius + style.bodyLength * 0.5, style.legLength);
        const hand = Math.random() > 0.5 ? 'left' : 'right';
        const loadout = {
            weapon: style.weapon,
            armor: style.armor,
            credits: 10 + Math.floor(Math.random() * 40),
        };
        enemies.push({ x: ex, y: ey, dx: evx, dy: evy, size: hitRadius, color: style.bodyColor, style, hand, halted: false, loadout });
    }

    function initEnemies() {
        enemies.length = 0;
        const count = computeEnemyCount();
        for (let i = 0; i < count; i++) {
            const style = pickEnemyStyle();
            spawnEnemy(style);
        }
    }

    function adjustEnemyCount() {
        const desired = computeEnemyCount();
        if (enemies.length > desired) {
            enemies.splice(desired, enemies.length - desired);
        } else {
            for (let i = enemies.length; i < desired; i++) {
                const style = pickEnemyStyle();
                spawnEnemy(style);
            }
        }
    }

    function clampEnemiesToScreen() {
        for (const enemy of enemies) {
            enemy.x = Math.max(enemy.size, Math.min(window.innerWidth - enemy.size, enemy.x));
            enemy.y = Math.max(enemy.size, Math.min(window.innerHeight - enemy.size, enemy.y));
        }
    }

    function centerPlayer() {
        if (!playerCentered) {
            player.x = window.innerWidth / 2;
            player.y = window.innerHeight / 2;
            playerCentered = true;
        }
        player.x = Math.max(player.radius, Math.min(window.innerWidth - player.radius, player.x));
        player.y = Math.max(player.radius, Math.min(window.innerHeight - player.radius, player.y));
    }

    function getStickVector(stick) {
        if (!stick || !stick.joystickActive) return { x: 0, y: 0 };
        const dx = (stick.joystickKnob.x - stick.joystickBase.x) / stick.joystickRadius;
        const dy = (stick.joystickKnob.y - stick.joystickBase.y) / stick.joystickRadius;
        return { x: dx, y: dy };
    }

    function normalizeAngle(angle) {
        const twoPi = Math.PI * 2;
        let a = angle % twoPi;
        if (a < -Math.PI) a += twoPi;
        if (a > Math.PI) a -= twoPi;
        return a;
    }

    function updatePlayerMovement() {
        const activeRenderMode = settings.pendingRenderMode || settings.renderMode;
        let dx = 0, dy = 0;
        if (keyState.up) dy -= 1;
        if (keyState.down) dy += 1;
        if (keyState.left) dx -= 1;
        if (keyState.right) dx += 1;

        const moveStick = getStickVector(joystickState);
        dx += moveStick.x;
        dy += moveStick.y;

        const length = Math.sqrt(dx * dx + dy * dy);
        const hasMoveInput = length > 0.001;

        if (activeRenderMode === '3d') {
            const forward = hasMoveInput ? -dy / length : 0;
            const strafe = hasMoveInput ? dx / length : 0;
            const cos = Math.cos(player.facing);
            const sin = Math.sin(player.facing);
            player.x += (strafe * cos - forward * sin) * player.speed * joystickState.maxSpeed;
            player.y += (strafe * sin + forward * cos) * player.speed * joystickState.maxSpeed;
        } else if (hasMoveInput) {
            const nx = dx / length;
            const ny = dy / length;
            player.x += nx * player.speed * joystickState.maxSpeed;
            player.y += ny * player.speed * joystickState.maxSpeed;
        }

        const lookStick = getStickVector(lookJoystickState);
        const lookMagnitude = Math.hypot(lookStick.x, lookStick.y);
        if (activeRenderMode === '3d' && lookMagnitude > 0.08) {
            const target = Math.atan2(lookStick.y, lookStick.x);
            const delta = normalizeAngle(target - player.facing);
            player.facing = normalizeAngle(player.facing + delta * 0.18);
        } else if (hasMoveInput) {
            const nx = dx / length;
            const ny = dy / length;
            player.facing = Math.atan2(ny, nx);
        }

        player.x = Math.max(player.radius, Math.min(window.innerWidth - player.radius, player.x));
        player.y = Math.max(player.radius, Math.min(window.innerHeight - player.radius, player.y));
    }

    function updateEnemies() {
        for (const enemy of enemies) {
            if (enemy.halted && !debugState.freezeOnCollision && enemy.dx === 0 && enemy.dy === 0) {
                enemy.halted = false;
                enemy.dx = (Math.random() - 0.5) * 2;
                enemy.dy = (Math.random() - 0.5) * 2;
            }
            if (enemy.halted && debugState.freezeOnCollision) continue;
            enemy.x += enemy.dx;
            enemy.y += enemy.dy;
            if (enemy.x < enemy.size || enemy.x > window.innerWidth - enemy.size) enemy.dx *= -1;
            if (enemy.y < enemy.size || enemy.y > window.innerHeight - enemy.size) enemy.dy *= -1;
        }
    }

    function addInventoryItem(kind, key) {
        const bucket = inventory.items[kind];
        if (!bucket[key]) bucket[key] = 0;
        bucket[key] += 1;
    }

    function addLoot(loadout) {
        addInventoryItem('weapons', loadout.weapon);
        addInventoryItem('armor', loadout.armor);
        inventory.credits += loadout.credits;
        notify(`Looted ${ITEM_CATALOG.weapons[loadout.weapon].label}, ${ITEM_CATALOG.armor[loadout.armor].label}, +${loadout.credits} cr`);
    }

    function sellItem(kind, key) {
        if (!inventory.items[kind][key]) return false;
        inventory.items[kind][key] -= 1;
        if (inventory.items[kind][key] <= 0) delete inventory.items[kind][key];
        const value = ITEM_CATALOG[kind][key]?.value || 0;
        inventory.credits += value;
        notify(`Sold ${ITEM_CATALOG[kind][key].label} for ${value} credits`);
        return true;
    }

    function buyItem(kind, key) {
        const cost = ITEM_CATALOG[kind][key]?.value || 0;
        if (inventory.credits < cost) return false;
        inventory.credits -= cost;
        addInventoryItem(kind, key);
        notify(`Bought ${ITEM_CATALOG[kind][key].label}`);
        return true;
    }

    function equipItem(kind, key) {
        if (!inventory.items[kind][key]) return false;
        inventory.equipped[kind === 'weapons' ? 'weapon' : 'armor'] = key;
        notify(`Equipped ${ITEM_CATALOG[kind][key].label}`);
        return true;
    }

    function handleCombat() {
        if (!actionState.attackPressed) return;
        for (let i = enemies.length - 1; i >= 0; i--) {
            const enemy = enemies[i];
            const dist = Math.hypot(enemy.x - player.x, enemy.y - player.y);
            if (dist < player.radius + enemy.size + 5) {
                enemies.splice(i, 1);
                killCount++;
                addLoot(enemy.loadout);
                notify('Fight won!');
                break;
            }
        }
    }

    function handleCollisions() {
        for (const enemy of enemies) {
            const dist = Math.hypot(enemy.x - player.x, enemy.y - player.y);
            if (dist < player.radius + enemy.size) {
                if (!enemy.halted) {
                    enemy.halted = true;
                    if (debugState.freezeOnCollision) {
                        enemy.dx = 0;
                        enemy.dy = 0;
                    }
                    notify('Fight triggered!');
                }
            }
        }
    }

    function spawnCharges(count = 3) {
        level2State.charges.length = 0;
        for (let i = 0; i < count; i++) {
            const padding = 20;
            const cx = level2State.containment.x + padding + Math.random() * (level2State.containment.size - padding * 2);
            const cy = level2State.containment.y + padding + Math.random() * (level2State.containment.size - padding * 2);
            level2State.charges.push({ x: cx, y: cy });
        }
    }

    function spawnWalls() {
        const { x, y, size } = level2State.containment;
        level2State.walls = [
            { x: x, y: y, width: size, height: 10, destroyed: false },
            { x: x, y: y + size - 10, width: size, height: 10, destroyed: false },
            { x: x, y: y, width: 10, height: size, destroyed: false },
            { x: x + size - 10, y: y, width: 10, height: size, destroyed: false },
        ];
    }

    function setupLevel2() {
        mission = 2;
        extractionReady = false;
        level2State.containment.x = Math.max(40, window.innerWidth / 2 - level2State.containment.size / 2);
        level2State.containment.y = Math.max(40, window.innerHeight / 2 - level2State.containment.size / 2);
        spawnCharges(3);
        spawnWalls();
        player.x = level2State.containment.x + level2State.containment.size / 2;
        player.y = level2State.containment.y + level2State.containment.size / 2;
        enemies.length = 0;
        notify('Containment breached: collect mining charges!');
    }

    function detonateCharges() {
        for (let i = level2State.planted.length - 1; i >= 0; i--) {
            const planted = level2State.planted[i];
            planted.timer -= 16;
            if (planted.timer <= 0) {
                let hitWall = false;
                for (const wall of level2State.walls) {
                    if (wall.destroyed) continue;
                    const dist = rectDistance(planted.x, planted.y, wall);
                    if (dist <= planted.radius) {
                        wall.destroyed = true;
                        hitWall = true;
                    }
                }
                const playerDist = Math.hypot(player.x - planted.x, player.y - planted.y);
                if (playerDist <= planted.radius) {
                    notify('Caught in the blast radius!');
                }
                if (hitWall) {
                    notify('Geo-mod breach successful! Head to extraction.');
                    mission = 3;
                    targetZone.x = Math.random() * (window.innerWidth - targetZone.size);
                    targetZone.y = Math.random() * (window.innerHeight - targetZone.size);
                } else {
                    notify('Blast missed the walls, charges respawned.');
                    spawnCharges(2);
                }
                level2State.planted.splice(i, 1);
            }
        }
    }

    function handleLevel2() {
        const { charges, containment, blastRadius } = level2State;
        for (let i = charges.length - 1; i >= 0; i--) {
            const charge = charges[i];
            const dist = Math.hypot(player.x - charge.x, player.y - charge.y);
            if (dist < player.radius + 12 && !level2State.carryingCharge) {
                level2State.carryingCharge = true;
                charges.splice(i, 1);
                actionState.label = 'Arm';
                notify('Picked up a mining charge');
            }
        }

        if (level2State.carryingCharge && actionState.attackPressed) {
            level2State.planted.push({ x: player.x, y: player.y, timer: 1800, radius: blastRadius });
            level2State.carryingCharge = false;
            actionState.label = 'Attack';
            notify('Charge armed! Exit the blast radius.');
        }

        detonateCharges();

        if (charges.length === 0 && !level2State.carryingCharge && level2State.planted.length === 0 && mission === 2) {
            spawnCharges(2);
        }

        if (mission === 3) {
            if (player.x >= targetZone.x && player.x <= targetZone.x + targetZone.size && player.y >= targetZone.y && player.y <= targetZone.y + targetZone.size) {
                mission = 1;
                killCount = 0;
                extractionReady = false;
                targetZone.x = Math.random() * (window.innerWidth - targetZone.size);
                targetZone.y = Math.random() * (window.innerHeight - targetZone.size);
                initEnemies();
                notify('Extraction reached. Level loop reset.');
            }
        }
    }

    function updateMission() {
        if (mission === 1 && killCount >= 5) {
            extractionReady = true;
            targetZone.x = window.innerWidth / 2 - targetZone.size / 2;
            targetZone.y = window.innerHeight / 2 - targetZone.size / 2;
        }
        if (mission === 2) {
            handleLevel2();
        }
        if (mission === 1 && extractionReady) {
            if (player.x >= targetZone.x && player.x <= targetZone.x + targetZone.size && player.y >= targetZone.y && player.y <= targetZone.y + targetZone.size) {
                notify('Extraction point reached. Mission ready.');
                setupLevel2();
            }
        }
    }

    function updateNotifications() {
        for (let i = notifications.length - 1; i >= 0; i--) {
            notifications[i].timer -= 16;
            if (notifications[i].timer <= 0) notifications.splice(i, 1);
        }
    }

    function update() {
        updatePlayerMovement();
        updateEnemies();
        handleCollisions();
        handleCombat();
        updateMission();
        updateNotifications();
    }

    function missionStateSnapshot() {
        return { mission, killCount, targetZone, level2State, extractionReady };
    }

    function inventorySnapshot() {
        return JSON.parse(JSON.stringify(inventory));
    }

    function skipToLevelTwo() {
        killCount = 5;
        setupLevel2();
    }

    return {
        player,
        enemies,
        missionState: missionStateSnapshot,
        initEnemies,
        adjustEnemyCount,
        clampEnemiesToScreen,
        centerPlayer,
        update,
        consumeNotifications,
        inventorySnapshot,
        sellItem,
        buyItem,
        equipItem,
        itemCatalog: ITEM_CATALOG,
        skipToLevelTwo,
    };
}
