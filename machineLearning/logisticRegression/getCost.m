function [cost] = getCost(h,inputY)
% define the costfunction in logic regression
%   �˴���ʾ��ϸ˵��   
cost = -inputY .* log(h+10^(-10))-(1-inputY) .* log(1-h+10^(-10));

end

