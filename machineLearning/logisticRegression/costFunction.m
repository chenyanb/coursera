function [jVal,gar] = costFunction(theta)
%compute cost and gardient
%
DataSet = [-0.001,0;-0.000001,0;-1,0;-2,0;-3,0;-4,0;-5,0;1,1;2,1;3,1;4,1;5,1;10,1;0.001,1];
x = DataSet(:,1);
y = DataSet(:,2);

rowNum = size(DataSet,1);
h = hypothesis(theta,x)
delta = h - y;
cost = getCost(h,y);

jVal = 1/rowNum * sum( cost)
gar(1) = 1/rowNum * sum(delta);
gar(2) = 1/rowNum * sum( delta .* x );

end

