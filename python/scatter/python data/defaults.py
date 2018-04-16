import matplotlib.pyplot as plt
import numpy as np

names = []
votes = []
# Split the dictionary of name:votes into two lists, one for names and one for vote count
for radish in counts:
    names.append(radish)
    votes.append(counts[radish])

# The X axis can just be numbered 0,1,2,3...
x = np.arange(len(counts))

plt.bar(x, votes)
plt.xticks(x + 0.5, names, rotation=90)