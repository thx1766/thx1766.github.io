k = 1;
AA = 0;
m = size(data,1);
for i = 1:1:m
    for j = 1:1:m
        if data(i,j) == 0
            break;
        end
        AA(k) = data(i,j);
        k = k+1;
    end
end
