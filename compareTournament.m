function compareTournament(x1, x2, n)
% Finding the max value between the matrices
figure(n);

% Finding the max value between the matrices
maxValue = max([x1(:);x2(:)]);

% Defining the bins for the histograms
bins = linspace(0, maxValue, maxValue + 1);

% Creating the histograms for each sample
a = hist(x1, bins);
b = hist(x2, bins);

% Plotting a bar graph with all the bins together
bar(bins, [a;b]')

% Changing the Layout of the histogram

% Title
title('Histograma Comparativo entre Torneio e Geracao');

% Legend
legend("Torneio", "Geracao");

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
