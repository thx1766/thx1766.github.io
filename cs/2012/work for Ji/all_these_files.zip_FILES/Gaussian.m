x = 5:.1:25;
mu = 15;
sigma = 4;
strech = 213;
pdfNormal = normPdf(x, mu, sigma);
hold off
plot(bin,'o')
hold on
plot(x, pdfNormal*strech)