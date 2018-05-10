require(dplyr)
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/scatter/data")

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
  
  b <-c(5,15,16,19)
  
  d <-c(2,4,8)
  
  e <-c(0,1,2,4)
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    marker <- sample(b, 1)
    
    mark <- sample(d, 1)
    
    marke <- sample(e, 1)
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])    
      x_axis <- numeric[1:10,a]
      m_axis <- numeric[10:20,a]
      mx_axis <- numeric[20:30,a]
      a <- a+1
      
      y_label <- names(numeric[a])
      
      y_axis <- numeric[1:10,a]  
      n_axis <- numeric[10:20,a]
      ny_axis <- numeric[20:30,a]
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots",paste("threeplotscalengenda_", x_label, ".jpg", sep = ""))
      
      jpeg(file=mypath)
      
      tit = paste("The ", title, " dataset", sep = "")
      
      #now <- plotme    
      plot(x_axis, y_axis,cex=1.3, main=tit, xlab=x_label, ylab= y_label, pch=marker, col="red") 
      
      points(m_axis, n_axis, pch=mark, col="blue") 
      
      points(mx_axis, ny_axis, pch=marke, col="green")
      
      legend("topright", c(x_label,y_label, "dummy"),cex=1, col=c("red","blue","green"),pch=c(marker, mark, marke))
      
      dev.off()
      
    }    
  }  
}