library(plotly)

Sys.setenv("plotly_username"="kumar05")
Sys.setenv("plotly_api_key"="IW5WKDoNWeWWon8haT7r")
#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')
#py.sign_in('ashishbbhayani', '5czLDBg2FdjjsBd1JXLh')
#py.sign_in('zanzy', '6tzTYqsLP9zeG91eamft')
#in same folder
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/bar/data")

out.file<-""

list.files(pattern=".csv$") # use the pattern argument to define a common pattern  for import files with regex. Here: .csv

# create a list from these files
list.filenames<-list.files(pattern=".csv$")

#each file in the folder
for(i in 1:length(list.filenames)){
  
  dataFrame <- read.table(list.filenames[i], header=TRUE, sep=",", stringsAsFactors=FALSE)
  
  #numeric <- dataFrame[,numerics]
  #numeric <- select_if(dataFrame, is_numeric)
  numeric  <- dataFrame[sapply(dataFrame, is.numeric)]
  title <- list.filenames[i]
  title <- sub('\\..*$', '', basename(title))
  print(title)
  
  a=1
  
  b <-c(0,1,2,3)
  
  lin <-c("1","o","b","c","s","S","h")
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    barsize <- sample(b, 1)
    
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])    
      x_axis <- numeric[1:3,a]
      xn_axis <- numeric[4:6,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:3,a]  
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots")
      
      filename <- paste("stackedlotlybar_" , x_label, ".png", sep = "" )
      
      tit = paste("A Dummy plotybar called ", title, " dataset", sep = "")
      
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
      
      Animals <- c(x_label, y_label, "dummy")
      
      data <- data.frame(Animals, x_axis, y_axis, xn_axis)
      
      p <- plot_ly(data, x = Animals, y = y_axis, type = 'bar', name = x_label) %>%
        add_trace(y = y_axis, name = y_label) %>%
        add_trace(y = xn_axis, name = "dummy") %>%
        
      layout(title = tit, xaxis = x, yaxis = y, barmode = 'stack')
      
      export(p, file = filename)  
      
      
      
    }    
    
  }  
}