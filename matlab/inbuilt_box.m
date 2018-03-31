load carsmall
%boxplot(MPG,Origin)
%title('Miles per Gallon by Vehicle Origin')
%xlabel('Country of Origin')
%ylabel('Miles per Gallon (MPG)')




%boxplot(MPG)
%xlabel('All Vehicles')
%ylabel('Miles per Gallon (MPG)')
%title('Miles per Gallon for All Vehicles')


%saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['figure_box''save''.jpg']));                         
  

rng default  % For reproducibility
x1 = normrnd(5,1,100,1);
x2 = normrnd(6,1,100,1);

%Create notched box plots of x1 and x2. Label each box with its corresponding mu value.

figure
boxplot([x1,x2],'Notch','on','Labels',{'mu = 5','mu = 6'})
title('Compare Random Data from Different Distributions')