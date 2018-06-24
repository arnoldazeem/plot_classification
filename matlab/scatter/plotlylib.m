path_to_downloaded_folder = '/home/azeem/Desktop/matlab/scatter/MATLAB-Online-master'; % fill in with your path
addpath(genpath(path_to_downloaded_folder))
% OR to use the demo account, do:
%ashishbbhayani
%5czLDBg2FdjjsBd1JXLh
%arnoldazeem2008
%MIRMEHz16jNDvNkGKwxL
plotly_username = 'ashishbbhayani' % a demo username you can use
plotly_key = '5czLDBg2FdjjsBd1JXLh' % a demo api key you can use
signin(plotly_username, plotly_key)  % sign in to plotly


files = dir('*.csv');
   
for file = files'
   clear v_is_cell 
   %read tablreade reads the whole file readtable
   %csvread should contain only numeric values
   
   T = readtable(file.name);
   
   str = file.name
   
   namess =str(1:strfind(str,'.')-1);
   
   display(str)
   
   s1 = "Quantile Quantile Plot with two distributions "
   
   tit  = strcat(s1,namess);
   
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

            fig = figure;
            hold on 
            
               % if k < length(labels)
                    x_axi = T.(k);
                    x_axis = (x_axi(1:18,:));
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:18,:));
                            y_lab = labels(k)
                                                                
                            b = [1,2,3,4];                 
                                                                                                                    
                            pos_fill = randi(length(b));                        
                            filler = b(pos_fill);                          
                                                                                                    
                             c = ['+','*','.','x','o','s']; 
                             pos_marker = randi(length(c));
                             card2 = c(pos_marker);
                             
                            plot(x_axis,y_axis,'.b')
                            p = polyfit(x_axis,y_axis,1)
                            f = polyval(p,x_axis);
                            hold on
                            plot(x_axis,f,'--r')
                            
                            %qqplot(x_axis,y_axis);

                                                 
                            title(tit);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                           folder = '/home/azeem/Desktop/matlab/scatter/matlab dataset';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                               
                           %saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['figur_plot' string '.jpg']));
                                                      
                            %--PLOTLY--%
                           response = fig2plotly(fig, 'filename', string);

                            %saveplotlyfig(response, ['scatter_check' string '.jpg'])  
                            saveplotlyfig(response,  ['st_fit' string '.png'])
                        end
                %end   
        end   
       
end    












