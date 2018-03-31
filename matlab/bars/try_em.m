A = [16 20 15 17 22 19 17]';
B = [22 15 16 16 16 18]';
C = [23 9 15 18 13 27 17 14 16 15 21 19 17]';

group = [    ones(size(A));
         2 * ones(size(B));
         3 * ones(size(C))];

figure
boxplot([A; B; C],group)
set(gca,'XTickLabel',{'A','B','C'})
