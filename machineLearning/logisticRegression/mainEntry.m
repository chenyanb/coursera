function [theta,finVal,exitFlag] = mainEntry()
%test the implementation of logic regression
%   �˴���ʾ��ϸ˵��
close;
clc;
x0 = [1,1];% initial solution
options = optimoptions('fminunc','GradObj','on','Algorithm','trust-region');
[theta,finVal,exitFlag] = fminunc(@costFunction,x0,options);

getGraph(theta);
test(theta);

end

