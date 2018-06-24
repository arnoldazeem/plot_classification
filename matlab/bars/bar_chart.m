%https://de.mathworks.com/matlabcentral/answers/143752-how-to-delete-non-numeric-columns-from-table
%https://perso.telecom-paristech.fr/eagan/class/igr204/datasets
%http://www.datplot.com/download/
%https://vincentarelbundock.github.io/Rdatasets/datasets.html

files = dir('*.csv');
   
for file = files'
   clear v_is_cell 
   %read tablreade reads the whole file readtable
   %csvread should contain only numeric values
   display(file.name)
   
   T = readtable(file.name);
   
   str = file.name
   
   namess =str(1:strfind(str,'.')-1);
   
   display(str)
   
   s1 = "A BAR CHART SHOWING RANDOM DATA QUALITY "
   
   tit  = strcat(s1,namess);
   
   %header
   V = T.Properties.VariableNames;
    %x= a.data; % x contains your data
    
    for i = 1:width(T)    
    v_is_cell(i) = iscell(T.(V{i}));
    end
    
    %use logical indexing to delete the required columns
    T(:,v_is_cell) = [];
    labels = T.Properties.VariableNames;
    
    %loop through the table
        for k= 1:length(labels)-1
            
            figure
            hold on 
            
               % if k < length(labels)
                   
                    x_axi = T.(k);
                    x_axis = (x_axi(1:4,:));
                    x_lab = labels(k);
                    put = k;
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:4,:));
                            y_lab = labels(k);
                            
                            %width of bar
                            b = [0.4,0.5,0.6,0.7,0.8,0.9];                                                                                                                                    
                            pos_fill = randi(length(b));
                            width = b(pos_fill);
                 
                            barh(y_axis,width,'FaceColor',[0 .5 .5],'EdgeColor',[0 .9 .9],'LineWidth',1.5)   
                            
                            title(s1);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/bars/';                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};                         
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/bars/',['e_bar' string '.jpg']));
                                                                               
                        end 
        end   
      
end    