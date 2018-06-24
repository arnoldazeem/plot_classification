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
   
   str = file.name
   
   namess =str(1:strfind(str,'.')-1);
  
   s1 = 'Plot Matrix with colors called  ' 

   tit  = strcat(s1,{' '}, namess);
   
   
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
                    x_axis = (x_axi(1:35,:));
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:35,:));
                            
                            
                            z_axis = (y_axi(1:35,:));
                            
                            y_lab = labels(k)
                            
                             %a = ['+','*','.','x','o','s','d','^','v','>','<','p','h'] 
                            
                            b = [1,2,3,4];                 
                                                                                                                    
                            pos_fill = randi(length(b));
                            
                            filler = b(pos_fill);
                            
                            %display(filler);
                            
                            sz = 40;
                                                    
                             c = ['+','*','.','x','o','s']; 
                             pos_marker = randi(length(c));
                             card2 = c(pos_marker);
                                                                     
                             plotmatrix(x_axis,y_axis,'*r'),  
                                           
                            title(tit);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/scatter/matlab dataset';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                                              
                           saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['matt_check' string '.jpg']));
                                                                                         
                        end
                %end   
        end   
       
end    
    
   