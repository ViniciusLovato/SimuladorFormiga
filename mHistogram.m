% Loading the data from the files
tournament16;
tournament64;
tournament128;
tournament512;

% Finding the max value between the matrices
maxValue = max([x16(:);x64(:);x128(:);x512(:)]);
bins = linspace(0, maxValue, maxValue + 1);

% Creating the histograms for each sample
% Formatting first histogram
subplot(2,2,1);
hist(x16, bins);
title("Histograma 16 samples");
configureHistogram(maxValue);

% Formatting first histogram
subplot(2,2,2);
hist(x64, bins);
title("Histograma 64 samples");
configureHistogram(maxValue);

% Formatting first histogram
subplot(2,2,3);
hist(x128, bins);
title("Histograma 128 samples");
configureHistogram(maxValue);

% Formatting first histogram
subplot(2,2,4);
hist(x512, bins);
title("Histograma 512 samples");
configureHistogram(maxValue);

% Saving the image
print('mHistogram.png', '-dpng');
