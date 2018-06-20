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
      x_axis <- numeric[1:5,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:5,a]  
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots")
      
      filename <- paste("plotlylinemark_" , x_label, ".png", sep = "" )
      
      tit = paste("Special line called ", title, " dataset", sep = "")
      
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
      
      #mode = 'lines+markers'
      # Plot data using plotly
      p <- plot_ly(x=x_axis, y=y_axis, type = 'scatter', mode = 'lines+markers') %>%
        
      layout(title = tit, xaxis = x, yaxis = y)
      
      
      export(p, file = filename)  
      
    }    
    
  }  
}
