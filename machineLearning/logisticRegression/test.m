function [] = test(theta )
%UNTITLED4 �˴���ʾ�йش˺�����ժҪ
%   �˴���ʾ��ϸ˵��
prompt = 'input the data you want to test? ';
testSample = input(prompt);
disp([ 'predict result is' , ': ' , num2str(hypothesis(theta,testSample)) ]);

end

