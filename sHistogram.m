% Loading the data from the files
tournament16;
tournament64;
tournament128;
tournament512;

% Finding the max value between the matrices
maxValue = max([x16(:);x64(:);x128(:);x512(:)]);

% Defining the bins for the histograms
bins = linspace(0, maxValue, maxValue + 1);

% Creating the histograms for each sample
a = hist(x16, bins);
b = hist(x64, bins);
c = hist(x128, bins);
d = hist(x512, bins);

% Plotting a bar graph with all the bins together
bar(bins, [a;b;c;d]')

% Changing the Layout of the histogram

% Title
title('Histograma de Torneios');

% Legend
legend("16 samples", "64 samples", "128 samples", "512 samples");

% Y label
% ylabel('Frequency');
ylabel('Numero de formigas');

% X label
xlabel("Score");

% X axis ticks
xAxis = gca();
set (xAxis, "XTick", [0:1:maxValue]);

% Saving the image
print('sHistogram.png', '-dpng');
