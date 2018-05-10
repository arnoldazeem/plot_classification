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
  title <- sub('\\..*$', '', basename(title))
  
  print(title)
  
  
  a=1
  
  
  for(n in 1:ncol(numeric)) {
    #each row,column is set
    
    
    if(a < ncol(numeric)){
      
      x_label <- names(numeric[a])    
      x_axis <- numeric[1:10,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:10,a]  
      
      tit = paste("Example box lattice called ", title, " dataset", sep = "")
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots",paste("myplotlinebox_", x_label, ".jpg", sep = ""))
      
      jpeg(file=mypath)
      
      #now <- plotme    
      boxplot(x_axis, xlab=x_label, ylab= y_label,main=tit) 
      
      
      dev.off()
      
    }    
    
  }  
}