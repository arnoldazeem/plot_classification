%https://de.mathworks.com/matlabcentral/answers/143752-how-to-delete-non-numeric-columns-from-table
%https://perso.telecom-paristech.fr/eagan/class/igr204/datasets
%http://www.datplot.com/download/
%https://vincentarelbundock.github.io/Rdatasets/datasets.html

files = dir('*.csv');
   
for file = files'
   clear v_is_cell 
    clear F
   %read tablreade reads the whole file readtable
   %csvread should contain only numeric values
   F = readtable(file.name);
  
   %header
   V = F.Properties.VariableNames;
   %x= a.data; % x contains your data
    
    for i = [1:width(F)]    
    v_is_cell(i) = iscell(F.(V{i}));
    end
    
    %use logical indexing to delete the required columns
    F(:,v_is_cell) = [];
    labels = F.Properties.VariableNames;
    
    %loop through the table
        for k= 1:length(labels)-1
            
            figure
            hold on 
            
               % if k < length(labels)
                   
                    x_axi = F.(k);
                    
                    x_axis = (x_axi(1:3,:));
                    
                    n_axis = (x_axi(4:6,:));
                                        
                
                    x_lab = labels(k);

                    put = k;
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = F.(k);
                            
                            y_axis = (y_axi(1:3,:));
                            
                            y_lab = labels(k);
                            
                           
                           y = [x_axis; n_axis; y_axis];
                           
                           bar(y);
                          
                                                                                                   
                           title(file.name);
                           xlabel(x_lab);
                           ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/scatter/';                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};                         
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/bar/',['figure_stacked' string '.jpg']));
                                                                               
                      end 
        end    
   end    







