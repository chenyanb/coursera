function [theta,finVal,exitFlag] = mainEntry()
%UNTITLED3 �˴���ʾ�йش˺�����ժҪ
%   �˴���ʾ��ϸ˵��
x0 = [0,0];%initial solution
options = optimoptions('fminunc','GradObj','on','Algorithm','trust-region');
[theta,finVal,exitFlag] = fminunc(@costFunction,x0,options)
end

