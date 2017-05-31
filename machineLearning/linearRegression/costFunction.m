function [jVal,gar] = costFunction(theta)
%compute cost and gardient
%
DataSet = [1,2;2,3;3,4;4,5;5,6;6,7;7,8;8,9];
x = DataSet(:,1);
y = DataSet(:,2);

rowNum = size(DataSet,1);
h = hypothesis(theta,x);%get the hypothesis value
delta = h-y;

jVal = 1/(2*rowNum) * sum( delta .^2 );
gar(1) = 1/rowNum * sum(delta);
gar(2) = 1/rowNum * sum( delta .* x );

end

