function [ output_args ] = getGraph(theta)
%UNTITLED5 此处显示有关此函数的摘要
%   此处显示详细说明
x1 = [-10:0.001:10];
x = theta(1) + theta(2) .* x1;

hypo = 1 ./ (1 + exp(-x));
finalRes = (hypo >= 0.5);
plot(x,finalRes,'r');
xlabel('inputData');
ylabel('predictValue');

end

