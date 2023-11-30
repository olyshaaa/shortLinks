document.addEventListener("DOMContentLoaded", function() {
    const button = document.querySelector(".go_button");
    const input = document.querySelector(".input");
    const link = document.querySelector(".link");
    let data;
    // button for deleting the url
    const deleteButton = document.querySelector(".delete_button");

    // getting an url
    button.addEventListener("click", async () => {
        const text = input.value;

        const response = await fetch('http://localhost:8080/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ url: text })
        });

        data = await response.json();
        console.log(data);
        link.href = `http://localhost:8080/my/${data.shortUrl}`;
        link.textContent = `http://localhost:8080/my/${data.shortUrl}`;
        // show the delete Button
        deleteButton.style.display = "block";
    });

    // delete an url
    deleteButton.addEventListener("click", async () => {
        const response = await fetch(`http://localhost:8080/my/${data.shortUrl}/delete`,
            )
        alert("Success")
        link.href = ""
        link.textContent = ""
        deleteButton.style.display = "none"
    });
});
