function [hypothesis] = hypothesis(theta,inputX)
%compute the hypothesis of linear regression
hypothesis = theta(1) + theta(2) .* inputX;
end

