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
                    x_axis = (x_axi(1:20,:));
                    n_axis = (x_axi(21:30,:));
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:20,:));
                            
                            m_axis = (y_axi(21:30,:));
                            
                            y_lab = labels(k)
                            
                              
                            b = [1,2,3,4];                 
                                                                                                                    
                            pos_fill = randi(length(b));
                            
                            filler = b(pos_fill);
                            
                            display(filler);
                            
                      
                           %some being filled and others not 
                            if filler == 1  
                             %only these markers can be filled 
                             w = ['o','s'];
                             pos_marker = randi(length(w));
                             card1 = w(pos_marker);  
                             
                             s0 = scatter(x_axis,y_axis, card1, 'filled');   hold on; 
                             legend('-DynamicLegend');
                             s1 = scatter(n_axis,m_axis);  hold on;
                             legend('-DynamicLegend');
                            else                                
                             c = ['+','*','.','x','o','s']; 
                             pos_marker = randi(length(c));
                             card2 = c(pos_marker); 
                             
                             s0 = scatter(x_axis,y_axis,card2);  hold on;
                             legend('-DynamicLegend');
                             s1 = scatter(n_axis,m_axis);  hold on;
                             legend('-DynamicLegend');
                             
                            end
                            
                            %scatter plot goes here     
                            
                            %bar(y_axis)
                            
                            
                            title(file.name);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                         
                           %legend(x_lab, y_lab,'location','Best');

                            
                           folder = '/home/azeem/Desktop/matlab/scatter/';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                           
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['figure_legend' string '.jpg']));
                         
                                                                
                        end
                %end   
        end   
       
end    
    