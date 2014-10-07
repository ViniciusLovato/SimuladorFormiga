% Loading the data from the files
tournament2;
tournament4;
tournament6;
tournament8;

% Finding the max value between the matrices
maxValue = max([x2(:);x4(:);x6(:);x8(:)]);

% Defining the bins for the histograms
bins = linspace(0, maxValue, maxValue + 1);

% Creating the histograms for each sample
a = hist(x2, bins);
b = hist(x4, bins);
c = hist(x6, bins);
d = hist(x8, bins);

% Plotting a bar graph with all the bins together
bar(bins, [a;b;c;d]')

% Changing the Layout of the histogram

% Title
title('Histograma de Torneios');

% Legend
legend("2 samples", "4 samples", "6 samples", "8 samples");

% Y label
% ylabel('Frequency');
ylabel('Numero de formigas');

% X label
xlabel("Score");

% X axis ticks
xAxis = gca();
set (xAxis, "XTick", [0:1:maxValue]);

% Saving the image
print('tournament.pdf', '-dpdf');
