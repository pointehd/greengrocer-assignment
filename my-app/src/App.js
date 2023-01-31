import {useEffect, useState} from "react";

const App = () => {

  const [categories, setCategories] = useState([]);
  useEffect(() => {
    apiGreengrocer()
    .then(data => {
      setCategories(data)
      setData({
        category: null,
        item: null,
      })
    });
  }, [])

  const [data, setData] = useState({
    category: null,
    item: null,
  })

  const [items, setItems] = useState([]);

  useEffect(() => {
    if (data.category == null) {
      return
    }
    apiGreengrocerCategory(data.category)
    .then(result => {
      setItems(result);
    })
  }, [data])

  const [price, setPrice] = useState(null);

  useEffect(() => {
    if (data.category == null || data.item == null) {
      return
    }
    apiGreengrocerPrice(data.category, data.item)
    .then(result => {
      setPrice(result);
    })
  }, [data])

  return (
      <div>
        <div>카테고리를 선택해주세요</div>
        {
            categories && (
                    categories.map((category, index) => {
                      return (
                          <button style={buttonStyle} key={index} onClick={() => {
                            setData({category: category, item: null})
                          }}>
                            {category}
                          </button>
                      )
                    }))
        }
        {
            items && (<div>
              <div style={marginTopStyle}>원하는 상품을 선택해주세요</div>
              {
                items.map((item, index) => {
                  return (
                      <button style={buttonStyle} key={index} onClick={() => {
                        setData({category: data.category, item: item})
                      }
                      }>
                        {item}
                      </button>
                  )
                })
              }

            </div>)
        }

        {
          data && data.item && (
                <div style={{
                  fontSize: "1.5rem",

                }}>선택하신 상품의 금액은 <b style={{
                  fontSize: "1.6rem",
                  fontWeight: "bold"
                }}>{price}</b> 입니다.</div>
            )

        }

      </div>
  )
}

export default App;

const apiGreengrocer = async () => {
  const response = await fetch("http://localhost:8080/greengrocer")
  .then(response => {
    return response.json();
  })
  .then(data => {
    return data['data'];
  });
  return response;
}

const apiGreengrocerCategory = async (category) => {
  const response = await fetch(`http://localhost:8080/greengrocer/${category}`)
  .then(response => {
    return response.json();
  })
  .then(data => {
    return data['data'];
  });
  return response;
}

const apiGreengrocerPrice = async (category, price) => {
  const response = await fetch(
      `http://localhost:8080/greengrocer/${category}/${price}`)
  .then(response => {
    return response.json();
  })
  .then(data => {
    return data['data'];
  })
  .then(data => {
    return data['price'];
  });
  return response;
}

const buttonStyle = {
  padding: 10,
  margin: 10
}

const marginTopStyle = {
  marginTop: 20
}