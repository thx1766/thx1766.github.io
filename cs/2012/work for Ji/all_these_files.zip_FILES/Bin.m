step = 10;
min = 0;
max = step;
bin = 0;
m=size(AA,2);
for i = 1:1:50;
    bin(i) = 0;
    for count = 1:1:m;
        if AA(count) > min && AA(count) <= max;
            bin(i) = bin(i)+1;
        end
    end
    min = min + step;
    max = max + step;
end
bin
sum(bin)
plot(bin)