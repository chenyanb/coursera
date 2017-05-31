function [theta,finVal,exitFlag] = mainEntry()
%UNTITLED3 此处显示有关此函数的摘要
%   此处显示详细说明
x0 = [0,0];%initial solution
options = optimoptions('fminunc','GradObj','on','Algorithm','trust-region');
[theta,finVal,exitFlag] = fminunc(@costFunction,x0,options)
end

