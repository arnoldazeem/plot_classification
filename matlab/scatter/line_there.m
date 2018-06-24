%https://de.mathworks.com/matlabcentral/answers/143752-how-to-delete-non-numeric-columns-from-table
%https://perso.telecom-paristech.fr/eagan/class/igr204/datasets
%http://www.datplot.com/download/
%https://vincentarelbundock.github.io/Rdatasets/datasets.html
%path_to_downloaded_folder = '/home/azeem/Desktop/matlab/scatter/MATLAB-Online-master'; % fill in with your path
%addpath(genpath(path_to_downloaded_folder))
% OR to use the demo account, do:
%plotly_username = 'arnoldazeem2008' % a demo username you can use
%plotly_key = 'LTSMMcxQkjZzDbKCQ3Lw' % a demo api key you can use
%signin(plotly_username, plotly_key)  % sign in to plotly

files = dir('*.csv');
   
for file = files'
   clear v_is_cell 
   %read tablreade reads the whole file readtable
   %csvread should contain only numeric values

   T = readtable(file.name);
   str = file.name
   
   namess =str(1:strfind(str,'.')-1);
   
  
   s1 = 'A graph showing data from ' 

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
                    x_axis = (x_axi(1:12,:));
                   
                    n_axis = (x_axi(13:24,:));
                    
                    xn_axis = (x_axi(26:38,:)); 
                    
                    x_lab = labels(k)
                    put = k
                    
                        if  k < length(labels)            
                            k = k+1;
                            y_axi = T.(k);
                            y_axis = (y_axi(1:12,:));
                                                        
                            m_axis = (y_axi(13:25,:));
                            my_axis = (y_axi(21:30,:));
                            y_lab = labels(k)
                            
 
                           
                            subplot(1,1,1);
                            s0 = scatter(x_axis,y_axis,30,n_axis,'MarkerFaceColor','y'); 
                            
                            colorbar;
                            caxis([-1000 1000])

                          
                                                                     
                            title(tit);
                            xlabel(x_lab);
                            ylabel(y_lab);
                            
                     
                           %legend(x_lab, y_lab,'location','Best');
                            
                           folder = '/home/azeem/Desktop/matlab/scatter/';
                           
                           %it as a cell had to convert to string 
                           string = y_lab{1};
                           
                           %response = fig2plotly(fig, 'filename', 'matlab-bubble-chart',  'strip', false);

                            %saveplotlyfig(response, ['scatter_check' string '.jpg'])  
                            %saveplotlyfig(response,  ['scatter_plotly' string '.png'])
                            
                            saveas(figure(put),fullfile('/home/azeem/Desktop/matlab/scatter/',['done_nal' string '.jpg']));
                         
                                                                
                        end
                %end   
        end   
       
end   