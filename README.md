
# Stroke chance prediction with machine learnong

This repository consist of four sub folders:

- Data
- EDA & ML log
- JavaWrapper
- Report

------------------------------------------------------------------------

## Folders 

The logbook about the data analysis and machine learning can be found in the EDA & ML log folder. All the data used in the logbook is retrieved from the data folder. The logbook folder contains four documents, the main log and a preliminary results document about the data analysis. Both in Rmarkdown and pdf file format. 

The Data folder itself are just a collection of used and created data files in different file formats.

The report folder contains the main report in word and pdf file format, the main report includes link to sub folders about the relevant part. 

This project also included a command-line application, a so call wrapper. This application is found in the JavaWrapper folder and has it's own readme included. 


----------------------------------------------------------------------------------------

## Libraries used in Rstudio

To successfully replicate the logbook results, some libraries needed to be downloaded to properly use the information. These libraries are: 

```
tidyverse
naniar
readr
ggplot2
knitr
pander
dplyr
DMwR* 
gridExtra
farff
cowplot
remotes
```
*The package 'DMwR' was removed from the CRAN repository trough several problems. The package can be retrieved by using the following command: remotes::install_github("cran/DMwR")* 


## Contact
- p.visser@st.hanze.nl
