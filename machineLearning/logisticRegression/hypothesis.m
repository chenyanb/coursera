function [hypothesis] = hypothesis(theta,inputX)
%compute the hypothesis of linear regression
h1 = theta(1) + theta(2) .* inputX;
hypothesis1 = 1 ./ (1 + exp(-h1));
hypothesis = (hypothesis1 >= 0.5);%obtain the predict 0 or 1 matrix

end

