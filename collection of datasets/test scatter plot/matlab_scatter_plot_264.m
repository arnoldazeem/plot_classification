

files = dir('*.csv');
cmap = hsv(3);   
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
                    x_axis = (x_axi(1:10,:));
                    x_lab = labels(k)
                    k = k+1;
                    put = k;
                    
                        if  k < length(labels)            
                            y_axi = T.(k);
                            y_axis = (y_axi(1:10,:));
                            y_lab = labels(k);
                            k = k+1;
                            
                            
                            if  k < length(labels)            
                                z_axi = T.(k);
                                z_axis = (z_axi(1:10,:));
                                z_lab = labels(k);
                                
                                gscatter(x_axis,y_axis,z_axis,'','xos');
                                
                            
                             end
                            
                             %a = ['+','*','.','x','o','s','d','^','v','>','<','p','h']
                                                        
                            
                            %b = [1,2,3,4];                 
                                          
                                                                             
                            %pos_fill = randi(length(b));
                            
                            %filler = b(pos_fill);
                            
                            %display(filler)
                           
                       
                            
                           %some being filled and others not 
                            %if filler == 1  
                             %only these markers can be filled 
                             %w = ['o','s'];
                             %pos_marker = randi(length(w));
                             
                             %card1 = w(pos_marker);
                             
                            %scatter(x_axis,y_axis, card1, 'filled')   
                            
                         
                            
                            %else                                
                             %c = ['+','*','.','x','o','s']; 
                             %pos_marker = randi(length(c));
                             %card2 = c(pos_marker);                                                      
                             %sgscatter(x_axis,y_axis,'','xos')
                             %scatter(x_axis,y_axis,card2)  
                             
                         %end
                            
                            %scatter plot goes here                                 
                            %bar(y_axis)
                                                    
                            title(file.name);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/scatter/';                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                                                      
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['figure_group_one' string '.jpg']));
                                                                                         
                        end
                end   
        end   
       
        