% Fisher Theorem
function r = fisher(generation)
s = std(generation);
f = mean(generation);
r = s*s/f;
end
