function [theta,finVal,exitFlag] = mainEntry()
%UNTITLED3 �˴���ʾ�йش˺�����ժҪ
%   �˴���ʾ��ϸ˵��
x0 = [1,1];% initial solution
options = optimoptions('fminunc','GradObj','on','Algorithm','trust-region');
[theta,finVal,exitFlag] = fminunc(@costFunction,x0,options)

x1 = [-10:0.001:10];
x = theta(1) + theta(2) .* x1;

hypo = 1 ./ (1 + exp(-x));
finalRes = hypo >= 0.5;
close;
plot(x,finalRes,'r');
xlabel('inputData');
ylabel('predictValue');

prompt = 'What is the data you want to test? ';
testSample = input(prompt);
disp([ 'predict result is' , ': ' , num2str(hypothesis(theta,testSample)) ])
end

