path_to_downloaded_folder = '/home/azeem/Desktop/matlab/scatter/MATLAB-Online-master'; % fill in with your path
addpath(genpath(path_to_downloaded_folder))
% OR to use the demo account, do:
%ashishbbhayani
%5czLDBg2FdjjsBd1JXLh
plotly_username = 'arnoldazeem2008' % a demo username you can use
plotly_key = 'MIRMEHz16jNDvNkGKwxL' % a demo api key you can use
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
   
   s1 = "A Graph showing data from "
   
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
            
                x_axi = T.(k);
                    x_axis = (x_axi(1:4,:));
                    xn_axis = (x_axi(5:7,:));
                    nxn_axis = (x_axi(8:10,:));
                    xxn_axis = (x_axi(11:14,:));
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                        k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:4,:));
                            yn_axis = (y_axi(5:7,:));
                            nyn_axis = (y_axi(8:10,:));
                            yyn_axis = (y_axi(11:14,:));
                            y_lab = labels(k)
                            
                           % Learn about API authentication here: https://plot.ly/matlab/getting-started

                       
                        trace1 = struct(...
                          'x', x_axis, ...
                          'y', y_axis, ...
                          'type', 'scatter');
                      
                        trace2 = struct(...
                          'x', xn_axis, ...
                          'y', yn_axis, ...
                          'type', 'scatter');
                      
                      
                      trace3 = struct(...
                          'x', nxn_axis, ...
                          'y', nyn_axis, ...
                          'type', 'scatter');
                        
                      trace4 = struct(...
                          'x', xxn_axis, ...
                          'y', yyn_axis, ...
                          'type', 'scatter');
                      
                        data = {trace1, trace2,trace3,trace4};
                       
                      

                           folder = '/home/azeem/Desktop/matlab/lines';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                           
                         
                         
                                    
                          saveplotlyfig(data, ['qe_g' string '.png'])  
                          
                        end
                %end   
        end   
       
end    













