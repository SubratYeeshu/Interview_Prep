int totalFruit(vector<int> &fruits)
{
    map<int, int> m;
    int i = 0;
    int j = 0;
    int n = fruits.size();
    int mx = INT_MIN;
    while (j < n)
    {
            m[fruits[j]]++;
        // if (m.size() < 2)
        // {
            //     j++;
        // }
        if (m.size() <= 2)
        {
                mx = max(mx, j - i + 1);
            j++;
        }
        else if (m.size() > 2)
        {
                while (m.size() > 2)
            {
                    m[fruits[i]]--;
                if (m[fruits[i]] == 0)
                {
                        m.erase(fruits[i]);
                }
                i++;
            }
            j++;
        }
    }
    if (mx == INT_MIN)
    {
            return 0;
    }
    return mx;
}