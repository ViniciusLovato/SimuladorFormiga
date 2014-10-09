function configureHistogram(maxValue)
% Y label
% ylabel('Frequency');
ylabel('Numero de formigas');
% X label
xlabel("Score");
% X axis ticks
xAxis = gca();
set (xAxis, "XTick", [0:1:maxValue]);
