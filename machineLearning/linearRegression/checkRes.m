function [res] = checkRes()
%UNTITLED2 此处显示有关此函数的摘要
%   此处显示详细说明
DataSet = [1,2;2,3;3,4;4,5;5,6;6,7;7,8;8,9];
x = DataSet(:,1);
y = DataSet(:,2);

fit1 = fittype({'x','1'},'coefficients',{'theta1','theta0'});
% y = theta1 * x + theta0;
res = fit(x,y,fit1)
plot(res,x,y);

end

