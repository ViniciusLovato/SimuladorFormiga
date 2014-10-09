function plotHistogram(x, n)
% Finding the max value between the matrices
figure(n);
maxValue = max(x);
bins = linspace(0, maxValue, maxValue + 1);

% Creating the histogram
hist(x, bins);
title("Histograma");
configureHistogram(maxValue);
