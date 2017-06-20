function [cost] = getCost(h,inputY)
% define the costfunction in logic regression
%   此处显示详细说明   
cost = -inputY .* log(h+10^(-10))-(1-inputY) .* log(1-h+10^(-10));

end

