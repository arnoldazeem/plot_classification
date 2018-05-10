library(plotly)

Sys.setenv("plotly_username"="kumar05")
Sys.setenv("plotly_api_key"="IW5WKDoNWeWWon8haT7r")
#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')
#py.sign_in('ashishbbhayani', '5czLDBg2FdjjsBd1JXLh')
#py.sign_in('zanzy', '6tzTYqsLP9zeG91eamft')
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/line/data")

out.file<-""

list.files(pattern=".csv$") # use the pattern argument to define a common pattern  for import files with regex. Here: .csv

# create a list from these files
list.filenames<-list.files(pattern=".csv$")

#each file in the folder
for(i in 1:length(list.filenames)){
  
  dataFrame <- read.table(list.filenames[i], header=TRUE, sep=",", stringsAsFactors=FALSE)
  
  numeric  <- dataFrame[sapply(dataFrame, is.numeric)]
  title <- list.filenames[i]
  title <- sub('\\..*$', '', basename(title))
  
  a=1
  
  b <-c(0,1,2,3,4,5,8,15,16,19)
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    marker <- sample(b, 1)
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])   
      print(x_label)
      x_axis <- numeric[1:7,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:7,a] 
      
      yn_axis <- numeric[7:13,a]
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots")
      
      filename <- paste("plotlyfourline_again" , x_label, ".png", sep = "" )
      
      tit = paste("A plotlyline called ", title, " dataset", sep = "")
      
      f <- list(
        family = "Courier New, monospace",
        size = 18,
        color = "#7f7f7f"
      )
      
      x <- list(
        title = x_label,
        titlefont = f
      )
      y <- list(
        title = y_label,
        titlefont = f
      )
      
      trace_0 <- x_axis
      trace_1 <- y_axis
      trace_2 <- rnorm(7, mean = -5)
      trace_3 <- yn_axis
      x <- c(1:7)
      
      data <- data.frame(x, trace_0, trace_1,trace_2,trace_3)
      
      p <- plot_ly(data, x = x, y = trace_0, name = x_label, type = 'scatter', mode = 'lines') %>%
        add_trace(y = trace_1, name = y_label, mode = 'lines') %>%
        add_trace(y = ~trace_2, name = 'dummy', mode = 'lines') %>%
        add_trace(y = ~trace_3, name = 'dummy2', mode = 'lines') %>%
        
      layout(title = tit,
             xaxis = list(title = x_label),
             yaxis = list (title = y_label))
        
      #mode = 'lines+markers'
      # Plot data using plotly
      export(p, file = filename)  
      
    }    
    
  }  
}
