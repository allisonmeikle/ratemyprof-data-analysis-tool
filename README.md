# RateMyProf Data Analysis Tool

This project is a data analysis and visualization tool developed for COMP 250 (Introduction to Computer Science), McGill University, Winter 2023. The project involved implementing a custom hash table data structure, applying object-oriented principles such as inheritance and abstraction, and designing efficient algorithms for parsing, storing, and analyzing a large dataset of professor reviews.

Key features include:

- **Custom Hash Table Implementation:** Built `MyHashTable` from scratch, supporting dynamic resizing, key-value storage, and efficient average O(1) operations for insertion, retrieval, and deletion.
- **Data Parsing and Analysis:** Implemented classes extending an abstract `DataAnalyzer` to extract structured information from 19,685 reviews, enabling analysis of ratings, word usage, and gender bias.
- **Analysis Tools:** Developed multiple analysis modules, including:
  - `RatingDistributionByProf` — computes distribution of quality ratings for individual professors.
  - `RatingDistributionBySchool` — computes average ratings and review counts for professors within a specific school.
  - `GenderByKeyword` — measures gendered differences in language usage in reviews.
  - `RatingByKeyword` — examines the distribution of ratings for reviews containing specific keywords.
  - `RatingByGender` — analyzes rating distributions by gender and rating type (quality or difficulty).
- **Data Visualization:** Integrated JavaFX GUI to display results in bar and line charts, enabling interactive exploration of trends and patterns in review data.

This project demonstrates skills in designing and implementing efficient data structures, parsing and analyzing large datasets, building modular and reusable object-oriented code, and creating interactive visualization tools in Java. It also highlights the application of programming concepts to investigate real-world phenomena, such as gender bias in teaching evaluations.
