# Load ggplot2 package for graphics/plotting
source("https://install-github.me/r-lib/processx")

library(processx)
library(ggplot2)
#in same folder
setwd("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/scatter/data")

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
      x_axis <- numeric[1:4,a]
      a <- a+1
      
      y_label <- names(numeric[a])   
      y_axis <- numeric[1:4,a]  
      
      mypath <- file.path("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/plots")
      
      filename <- paste("ggplot_" , x_label, sep = "," )
      
      # Plot data using ggplot2
      ggplot(data=numeric, aes(x=x_label, y=y_label)) +
        geom_point()
     
      ggsave(filename, plot = last_plot(), device = "jpeg", path = mypath,
             width = NA, height = NA, dpi = 300)
      
    }    
    
  }  
}
