function [] = test(theta )
%UNTITLED4 此处显示有关此函数的摘要
%   此处显示详细说明
prompt = 'input the data you want to test? ';
testSample = input(prompt);
disp([ 'predict result is' , ': ' , num2str(hypothesis(theta,testSample)) ]);

end

