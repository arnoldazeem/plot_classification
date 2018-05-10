 
require(dplyr)
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/line/data")

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
  
  a=1
  
  b <-c(0,1,2,3,4,5,8,15,16,19)
  
  lin <-c("l","o","b","c","s","S","h")
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    marker <- sample(b, 1)
    lins <- sample(lin, 1)
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])    
      x_axis <- numeric[1:2,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:2,a]  
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots",paste("myplotlinelegend_", x_label, ".jpg", sep = ""))
      
      jpeg(file=mypath)
      
      
      tit = paste("Good scatter called ", title, " dataset", sep = "")
      #now <- plotme    
      p <- plot_ly(numeric, x = ~x_axis, y = ~y_axis, type = 'scatter', mode = 'lines')
      
    
      
      #legend(x="topright",y=0.92,legend=c(x_label,y_label),lty=1:2,col=c("blue","red"),cex=0.8) 
      
      dev.off()
      
    }    
    
  }  
}