// QUESTION: 3005. Count Elements With Maximum Frequency
// LINK: https://leetcode.com/problems/count-elements-with-maximum-frequency/description/?envType=daily-question&envId=2025-09-22

// SOLUTION: 

class Solution {
    public int maxFrequencyElements(int[] nums) {
        
        
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums)
        {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        for(int freq : freqMap.values())
        {
            maxFreq = Math.max(maxFreq, freq);
        }

        int totalCount = 0;
        for(int freq : freqMap.values())
        {
            if(freq == maxFreq)
            {
                totalCount += freq;
            }
        }
        return totalCount;
    }
}




I have a solid foundation in software development and enjoy building reliable, efficient solutions. My experience covers both backend and frontend development, with strong coding skills in C#, ASP.NET MVC, ADO.NET, .NET Core, and Web API. On the front end, I have worked with React.js, JavaScript, CSS, and PWAs, creating responsive and user-friendly applications.

I am comfortable working with SQL Server for writing optimized queries, designing schemas, and troubleshooting database-related issues. I also have exposure to ActiveMQ and RabbitMQ, which helps me work on messaging and event-driven systems effectively.

From a DevOps perspective, I have used Git for version control, Azure Pipelines for CI/CD, and I can create and manage Docker containers to streamline deployments. I believe in writing clean, maintainable code and actively participate in code reviews to improve overall quality.

I am also experienced in writing unit tests (using JEST, MSTest/NUnit) and performing root cause analysis to fix issues quickly. Over time, I have developed good debugging skills and a habit of documenting processes for smoother knowledge sharing.

Apart from technical skills, I bring strong communication abilities, a willingness to collaborate with teams and customers, and the ability to manage multiple tasks without losing focus. I am quick to adapt to new tools and technologies, and I enjoy learning continuously to stay updated.