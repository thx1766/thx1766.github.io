TF = isnan(bin);
Dis = 0;
for i=1:1:40
    if TF(i) == 1
        break;
    end
        Dis(i) = abs(bin(i)-y(i));
end
tempsumDis = sum(Dis);