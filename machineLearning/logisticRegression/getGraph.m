function [ output_args ] = getGraph(theta)
%UNTITLED5 �˴���ʾ�йش˺�����ժҪ
%   �˴���ʾ��ϸ˵��
x1 = [-10:0.001:10];
x = theta(1) + theta(2) .* x1;

hypo = 1 ./ (1 + exp(-x));
finalRes = (hypo >= 0.5);
plot(x,finalRes,'r');
xlabel('inputData');
ylabel('predictValue');

end

