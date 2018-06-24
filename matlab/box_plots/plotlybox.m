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
   
   s1 = "The title is "
   
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
                    x_axis = (x_axi(1:12,:));
                    xn_axis = (x_axi(13:25,:));
                    nxn_axis = (x_axi(13:18,:));
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                        k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:12,:));
                            yn_axis = (y_axi(7:12,:));
                            nyn_axis = (y_axi(13:19,:));
                            
                            y_lab = labels(k)
                            
                           % Learn about API authentication here: https://plot.ly/matlab/getting-started

                        x = {'day 1' 'day 1' 'day 1' 'day 1' 'day 1' 'day 1'}
                         
                        y = {'day 2' 'day 2' 'day 2' 'day 2' 'day 2' 'day 2'}
                        
                        z = {'day 3' 'day 3' 'day 3' 'day 3' 'day 3' 'day 3'}

                        trace1 = struct(...
                          'y', x_axis, ...
                          'x', {x}, ...
                          'name', x_lab, ...
                          'marker', struct('color', '#3D9970'), ...
                          'type', 'box');
                      
                        trace2 = struct(...
                          'y', y_axis, ...
                          'x', {y}, ...
                          'name', y_lab, ...
                          'marker', struct('color', '#FF4136'), ...
                          'type', 'box');
                      
                        trace3 = struct(...
                          'y', xn_axis, ...
                          'x', {z}, ...
                          'name', 'dummy', ...
                          'marker', struct('color', '#FF851B'), ...
                          'type', 'box');
                      
                      
                        data = {trace1, trace2, trace3};
                        
                        layout = struct(...
                            'yaxis', struct(...
                              'title', tit, ...
                              'zeroline', false), ...
                            'boxmode', 'group');
                       
                            title(tit);
                            xlabel(x_lab);
                            ylabel(y_lab);

                           folder = '/home/azeem/Desktop/matlab/box_plot';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                 
                                    
                          
                          saveplotlyfig(data, ['o2_chaging' string '.png'])                           
                        end
                %end   
        end   
       
end    












