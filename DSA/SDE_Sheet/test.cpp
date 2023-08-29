int hpat = 0, htxt = 0;
int d = 31;
int p = 1e9+7;
for (int i = 0; i < pat.size(); i++)
{
    hpat *= d;
    hpat = hpat + (((pat[i] - 'a' + 1)) % p);
}
int l = 0, r = 0;
while (r < txt.size())
{
    htxt *= d;
    htxt = htxt + ((txt[r] - 'a' + 1) % p);
    if (r - l + 1 == pat.size())
    {
        if (htxt == hpat)
            cout << "Match at " << l;
        htxt = htxt - (((txt[l] - 'A' + 1) * pow(d, r - l)));
        l++;
    }
    r++;
}