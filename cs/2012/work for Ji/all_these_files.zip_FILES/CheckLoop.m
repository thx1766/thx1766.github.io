sumDis = 100;
for k=-5.0e+008:0.1e+008:5.0e+008
    tempA = A + k;
    for l=-10:.1:10
        tempB = B + l;
        y = ((tempA)./((C*x+D).^5)).*(1./(e.^(tempB./(C*x+D))));
        run SimpleDistanceLoop
        if tempsumDis < sumDis
            sumDis = tempsumDis
            bestA = tempA
            bestB = tempB
        end
    end
end
hold off
y = ((bestA)./((C*x+D).^5)).*(1./(e.^(bestB./(C*x+D))));
plot(x,y)
hold on
plot(bin,'o')