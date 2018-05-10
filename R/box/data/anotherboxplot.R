require(dplyr)
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/box/data")

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
  
  a=1
  
  b <-c(0,1,2,3,4,5,8,15,16,19)
  
  lin <-c("1","o","b","c","s","S","h")
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    marker <- sample(b, 1)
    lins <- sample(lin, 1)
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])    
      x_axis <- numeric[1:10,a]
      w_axis <- numeric[10:20,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:10,a]  
      z_axis <- numeric[10:20,a]  
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots",paste("myplotlineboxmulti_", x_label, ".png", sep = ""))
      
      png(file=mypath)
      
      #now <- plotme    
      boxplot(x_axis,y_axis,w_axis,z_axis, xlab=x_label, ylab= y_label) 
      
      
      dev.off()
      
    }    
    
  }  
}