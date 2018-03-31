%https://de.mathworks.com/matlabcentral/answers/143752-how-to-delete-non-numeric-columns-from-table
%https://perso.telecom-paristech.fr/eagan/class/igr204/datasets
%http://www.datplot.com/download/
%https://vincentarelbundock.github.io/Rdatasets/datasets.html

files = dir('*.csv');
   
for file = files'
   clear v_is_cell 
   %read tablreade reads the whole file readtable
   %csvread should contain only numeric values

   T = readtable(file.name);
   %header
   V = T.Properties.VariableNames;
    %x= a.data; % x contains your data
    
    for i = [1:width(T)]    
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
                    x_axis = (x_axi(2:4,:));
                    n_axis = (x_axi(6:9,:));
                    
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(2:4,:));
                            m_axis = (y_axi(6:9,:));
                            y_lab = labels(k)
                                                                                                               
                            plot(x_axis,y_axis)
                            hold on
                            legend('-DynamicLegend');
                            
                            plot(n_axis,m_axis)
                            legend('-DynamicLegend');
                             
                            title(file.name);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/line_plot/';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                                                   
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['figure_line_legend' string '.jpg']));                         
                                                                
                        end
                %end   
        end   
       
end    
    