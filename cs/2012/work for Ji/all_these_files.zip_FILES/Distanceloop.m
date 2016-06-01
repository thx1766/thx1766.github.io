TF = isnan(OxphosL);
for i=1:1:40
    if TF(i) == 1
        break;
    end
    for j=1:1:40
        Data(j) = sqrt((x(i)-x(j))^2+(OxphosL(i)-y(j))^2);
    end
    Dis(i) = min(Data);
end
tempsumDis = sum(Dis);